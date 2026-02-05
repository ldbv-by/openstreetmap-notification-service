INSERT INTO notification_data.notification_types (id, description) VALUES
    ('INFO', 'Information'),
    ('QUALITY_CHECK', 'Qualitätsprüfung')
ON CONFLICT (object_type) DO NOTHING;