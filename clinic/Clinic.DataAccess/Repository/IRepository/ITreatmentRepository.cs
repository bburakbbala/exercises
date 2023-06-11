using Clinic.Models;

namespace Clinic.DataAccess.Repository.IRepository
{
    public interface ITreatmentRepository : IRepositoryAsync<Treatment>
    {
        void Update(Treatment treatment);
    }
}